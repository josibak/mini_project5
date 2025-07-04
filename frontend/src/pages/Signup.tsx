import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { BookOpen, Eye, EyeOff, Home } from 'lucide-react';
import { Button } from '@/components/ui/button';
import axios from 'axios';

const Signup = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      alert('비밀번호가 일치하지 않습니다.');
      return;
    }

    try {
      const response = await axios.post(
        `${import.meta.env.VITE_API_BASE_URL}/members`,
        {
          name: formData.name,
          email: formData.email,
          password: formData.password
        },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      );

      alert('회원가입이 완료되었습니다!');
      navigate('/login');
    } catch (error) {
      console.error(error);
      alert('회원가입에 실패했습니다. 다시 시도해주세요.');
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-warm-brown-50 to-warm-brown-100 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <div className="text-center mb-8">
          <div className="flex items-center justify-between mb-6">
            <Link to="/" className="inline-flex items-center gap-2 text-warm-brown-700 hover:text-warm-brown-800">
              <Home className="w-5 h-5" />
              <span className="text-sm">홈으로</span>
            </Link>
            <Link to="/" className="inline-flex items-center gap-2">
              <BookOpen className="w-8 h-8 text-warm-brown-700" />
              <span className="text-2xl font-bold text-warm-brown-800">StoryBloom</span>
            </Link>
            <div className="w-16"></div>
          </div>
          <h1 className="text-3xl font-light text-gray-900 mb-2">회원가입</h1>
          <p className="text-gray-600">새로운 이야기의 세계로 떠나보세요</p>
        </div>

        <div className="bg-white rounded-2xl shadow-lg border border-warm-brown-200 p-8">
          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-2">
                이름
              </label>
              <input
                type="text"
                id="name"
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                className="w-full px-4 py-3 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                placeholder="이름을 입력하세요"
                required
              />
            </div>

            <div>
              <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">
                이메일
              </label>
              <input
                type="email"
                id="email"
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                className="w-full px-4 py-3 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                placeholder="이메일을 입력하세요"
                required
              />
            </div>

            <div>
              <label htmlFor="password" className="block text-sm font-medium text-gray-700 mb-2">
                비밀번호
              </label>
              <div className="relative">
                <input
                  type={showPassword ? 'text' : 'password'}
                  id="password"
                  value={formData.password}
                  onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                  className="w-full px-4 py-3 pr-12 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                  placeholder="비밀번호를 입력하세요"
                  required
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-gray-700"
                >
                  {showPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                </button>
              </div>
            </div>

            <div>
              <label htmlFor="confirmPassword" className="block text-sm font-medium text-gray-700 mb-2">
                비밀번호 확인
              </label>
              <div className="relative">
                <input
                  type={showConfirmPassword ? 'text' : 'password'}
                  id="confirmPassword"
                  value={formData.confirmPassword}
                  onChange={(e) => setFormData({ ...formData, confirmPassword: e.target.value })}
                  className="w-full px-4 py-3 pr-12 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                  placeholder="비밀번호를 다시 입력하세요"
                  required
                />
                <button
                  type="button"
                  onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                  className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-gray-700"
                >
                  {showConfirmPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                </button>
              </div>
            </div>

            <div className="flex items-center">
              <input
                type="checkbox"
                id="terms"
                className="rounded border-warm-brown-300 text-warm-brown-600 focus:ring-warm-brown-500"
                required
              />
              <label htmlFor="terms" className="ml-2 text-sm text-gray-600">
                <Link to="/terms" className="text-warm-brown-600 hover:text-warm-brown-700">이용약관</Link> 및{' '}
                <Link to="/privacy" className="text-warm-brown-600 hover:text-warm-brown-700">개인정보처리방침</Link>에 동의합니다
              </label>
            </div>

            <Button
              type="submit"
              className="w-full bg-warm-brown-700 hover:bg-warm-brown-800 text-white py-3 text-lg font-medium rounded-lg"
            >
              회원가입
            </Button>
          </form>

          <div className="mt-6 text-center">
            <p className="text-gray-600">
              이미 계정이 있으신가요?{' '}
              <Link to="/login" className="text-warm-brown-600 hover:text-warm-brown-700 font-medium">
                로그인
              </Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Signup;
